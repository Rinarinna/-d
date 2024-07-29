package com.example.filestorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    @PostMapping
    public ResponseEntity<Long> createFile(@RequestBody FileEntity file) {
        fileRepository.save(file);
        return ResponseEntity.ok(file.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileEntity> getFile(@PathVariable Long id) {
        Optional<FileEntity> file = fileRepository.findById(id);
        return file.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<FileEntity>> getFiles(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<FileEntity> files = fileRepository.findAll(pageable);
        return ResponseEntity.ok(files);
    }
}
