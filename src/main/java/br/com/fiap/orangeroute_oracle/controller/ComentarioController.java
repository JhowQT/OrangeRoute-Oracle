package br.com.fiap.orangeroute_oracle.controller;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.orangeroute_oracle.dto.ComentarioCreateDTO;
import br.com.fiap.orangeroute_oracle.dto.ComentarioResponseDTO;
import br.com.fiap.orangeroute_oracle.service.ComentarioService;

@RestController
@RequestMapping("/comentarios")
@CrossOrigin(origins = "*")
public class ComentarioController {

    @Autowired
    private ComentarioService service;

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        List<ComentarioResponseDTO> comentarios = service.listarTodos();

        List<Map<String, Object>> data = comentarios.stream().map(c -> {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("idComentario", c.getIdComentario());
            item.put("idTrilhaCarreira", c.getIdTrilhaCarreira());
            item.put("idUsuario", c.getIdUsuario());
            item.put("conteudoComentario", c.getConteudoComentario());
            item.put("nomeUsuario", c.getNomeUsuario());
            item.put("tituloTrilha", c.getTituloTrilha());
            item.put("_links", Map.of(
                    "self", "/comentarios/" + c.getIdComentario(),
                    "trilha", "/trilhas/" + c.getIdTrilhaCarreira(),
                    "usuario", "/usuario/" + c.getIdUsuario(),
                    "all", "/comentarios"
            ));
            return item;
        }).collect(Collectors.toList());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("count", data.size());
        response.put("data", data);
        response.put("_links", Map.of(
                "self", "/comentarios",
                "create", "/comentarios (POST)"
        ));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/trilha/{idTrilha}")
    public ResponseEntity<?> listarPorTrilha(@PathVariable Long idTrilha) {
        List<ComentarioResponseDTO> comentarios = service.listarPorTrilha(idTrilha);

        List<Map<String, Object>> data = comentarios.stream().map(c -> {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("idComentario", c.getIdComentario());
            item.put("idTrilhaCarreira", c.getIdTrilhaCarreira());
            item.put("idUsuario", c.getIdUsuario());
            item.put("conteudoComentario", c.getConteudoComentario());
            item.put("nomeUsuario", c.getNomeUsuario());
            item.put("tituloTrilha", c.getTituloTrilha());
            item.put("_links", Map.of(
                    "self", "/comentarios/" + c.getIdComentario(),
                    "trilha", "/trilhas/" + idTrilha,
                    "usuario", "/usuario/" + c.getIdUsuario(),
                    "all", "/comentarios"
            ));
            return item;
        }).collect(Collectors.toList());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("count", data.size());
        response.put("data", data);
        response.put("_links", Map.of(
                "self", "/comentarios/trilha/" + idTrilha,
                "trilha", "/trilhas/" + idTrilha,
                "create", "/comentarios (POST)",
                "all", "/comentarios"
        ));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ComentarioCreateDTO dto) {
        ComentarioResponseDTO novo = service.criar(dto);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("idComentario", novo.getIdComentario());
        body.put("idTrilhaCarreira", novo.getIdTrilhaCarreira());
        body.put("idUsuario", novo.getIdUsuario());
        body.put("conteudoComentario", novo.getConteudoComentario());
        body.put("nomeUsuario", novo.getNomeUsuario());
        body.put("tituloTrilha", novo.getTituloTrilha());
        body.put("_links", Map.of(
                "self", "/comentarios/" + novo.getIdComentario(),
                "trilha", "/trilhas/" + novo.getIdTrilhaCarreira(),
                "usuario", "/usuario/" + novo.getIdUsuario(),
                "all", "/comentarios"
        ));

        return ResponseEntity.ok(body);
    }
}
