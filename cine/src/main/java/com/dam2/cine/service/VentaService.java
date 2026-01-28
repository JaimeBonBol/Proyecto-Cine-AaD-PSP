package com.dam2.cine.service;

import com.dam2.cine.model.*;
import com.dam2.cine.repository.FuncionRepository;
import com.dam2.cine.repository.UsuarioRepository;
import com.dam2.cine.repository.VentaRepository;
import com.dam2.cine.dto.request.EntradaRequestDTO;
import com.dam2.cine.dto.request.VentaRequestDTO;
import com.dam2.cine.dto.response.VentaResponseDTO;
import com.dam2.cine.mapper.VentaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VentaService {

    private final VentaRepository ventaRepository;
    private final UsuarioRepository usuarioRepository;
    private final FuncionRepository funcionRepository;
    private final VentaMapper ventaMapper;

    public List<VentaResponseDTO> findAll() {
        return ventaRepository.findAll().stream()
                .map(ventaMapper::toDTO)
                .toList();
    }

    public VentaResponseDTO findById(Long id) {
        return ventaRepository.findById(id)
                .map(ventaMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrada con ID: " + id));
    }

    @Transactional
    public VentaResponseDTO save(VentaRequestDTO dto) {
        Venta venta = new Venta();

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        venta.setUsuario(usuario);

        venta.setFecha(LocalDateTime.now());
        venta.setEstado("PENDIENTE");
        venta.setMetodoPago("TARJETA");

        procesarEntradasYTotal(venta, dto.getEntradas());

        Venta ventaGuardada = ventaRepository.save(venta);
        return ventaMapper.toDTO(ventaGuardada);
    }

    @Transactional
    public VentaResponseDTO update(Long id, VentaRequestDTO dto) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrada"));

        if (!venta.getUsuario().getId().equals(dto.getUsuarioId())) {
            Usuario nuevoUsuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nuevo usuario no encontrado"));
            venta.setUsuario(nuevoUsuario);
        }

        venta.getEntradas().clear();
        procesarEntradasYTotal(venta, dto.getEntradas());

        Venta ventaActualizada = ventaRepository.save(venta);
        return ventaMapper.toDTO(ventaActualizada);
    }

    @Transactional
    public void delete(Long id) {
        if (!ventaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede borrar. Venta no encontrada");
        }
        ventaRepository.deleteById(id);
    }

    private void procesarEntradasYTotal(Venta venta, List<EntradaRequestDTO> entradasDTO) {
        double total = 0.0;
        List<Entrada> nuevasEntradas = new ArrayList<>();

        for (EntradaRequestDTO entradaDTO : entradasDTO) {
            Funcion funcion = funcionRepository.findById(entradaDTO.getFuncionId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Función no encontrada"));

            Entrada entrada = new Entrada();
            entrada.setFuncion(funcion);
            entrada.setFila(entradaDTO.getFila());
            entrada.setAsiento(entradaDTO.getAsiento());
            entrada.setCodigo(UUID.randomUUID().toString().substring(0, 8));
            entrada.setEstado(EstadoEntrada.PENDIENTE);
            entrada.setVenta(venta);
            
            nuevasEntradas.add(entrada);
            total += funcion.getPrecio();
        }

        if (venta.getEntradas() == null) {
            venta.setEntradas(nuevasEntradas);
        } else {
            venta.getEntradas().addAll(nuevasEntradas);
        }
        venta.setTotal(total);
    }
}