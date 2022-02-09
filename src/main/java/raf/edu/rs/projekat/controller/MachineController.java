package raf.edu.rs.projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.edu.rs.projekat.model.Machine;
import raf.edu.rs.projekat.model.User;
import raf.edu.rs.projekat.service.MachineService;
import raf.edu.rs.projekat.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/machines")
@CrossOrigin
public class MachineController {

    private final MachineService machineService;

    @Autowired
    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Machine> getAllMachines() {
        return machineService.findAll();
    }

    @GetMapping(value = "/{machineId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMachineById(@PathVariable("machineId") Long machineId) {
        Optional<Machine> optionalMachine = machineService.findById(machineId);
        if (optionalMachine.isPresent()) {
            return ResponseEntity.ok(optionalMachine.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Machine createMachine(@RequestBody Machine machine) {
        return machineService.save(machine);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Machine updateMachine(@RequestBody Machine machine) {
        return machineService.save(machine);
    }

    @DeleteMapping(value = "/{machineId}")
    public ResponseEntity<?> deleteMachine(@PathVariable("machineId") Long machineId){
        machineService.deleteById(machineId);
        return ResponseEntity.ok().build();
    }

}
