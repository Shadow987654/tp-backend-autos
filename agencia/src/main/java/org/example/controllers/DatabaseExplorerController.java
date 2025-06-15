package org.example.controllers;

import org.example.services.DatabaseExplorerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/db")
public class DatabaseExplorerController {

    private final DatabaseExplorerService databaseExplorerService;
    
    @Autowired
    public DatabaseExplorerController(DatabaseExplorerService databaseExplorerService) {
        this.databaseExplorerService = databaseExplorerService;
    }
    
    @GetMapping("/tables")
    public ResponseEntity<List<String>> getAllTables() {
        return ResponseEntity.ok(databaseExplorerService.getAllTables());
    }
    
    @GetMapping("/tables/{tableName}")
    public ResponseEntity<List<Map<String, Object>>> getTableRecords(@PathVariable String tableName) {
        return ResponseEntity.ok(databaseExplorerService.getAllRecords(tableName));
    }
    
    @PostMapping("/query")
    public ResponseEntity<List<Map<String, Object>>> executeQuery(@RequestBody String sql) {
        return ResponseEntity.ok(databaseExplorerService.executeNativeQuery(sql));
    }
}
