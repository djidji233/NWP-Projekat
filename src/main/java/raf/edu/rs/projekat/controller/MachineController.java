package raf.edu.rs.projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.edu.rs.projekat.model.Machine;
import raf.edu.rs.projekat.model.MachineStatus;
import raf.edu.rs.projekat.model.User;
import raf.edu.rs.projekat.service.MachineService;
import raf.edu.rs.projekat.service.UserService;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
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
    public List<Machine> getAllMachines(@RequestHeader(name = "Authorization") String token) throws UnsupportedEncodingException{
        return machineService.findAll(token);
    }

    @GetMapping(value = "/{machineId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMachineById(@PathVariable("machineId") Long machineId,@RequestHeader(name = "Authorization") String token) throws UnsupportedEncodingException {
        Optional<Machine> optionalMachine = machineService.findById(machineId,token);
        if (optionalMachine.isPresent()) {
            return ResponseEntity.ok(optionalMachine.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


//
//    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public Machine updateMachine(@RequestBody Machine machine) {
//        return machineService.save(machine);
//    }
//
//    @DeleteMapping(value = "/{machineId}")
//    public ResponseEntity<?> deleteMachine(@PathVariable("machineId") Long machineId){
//        machineService.deleteById(machineId);
//        return ResponseEntity.ok().build();
//    }

    @PutMapping(value = "/start",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public MachineStatus startMachine(@RequestParam Long machineId, @RequestHeader(name = "Authorization") String token) throws UnsupportedEncodingException, InterruptedException {
        return machineService.startMachine(machineId, token);
    }

    @PutMapping(value = "/stop",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public MachineStatus stopMachine(@RequestParam Long machineId, @RequestHeader(name = "Authorization") String token) throws UnsupportedEncodingException, InterruptedException {
        return machineService.stopMachine(machineId, token);
    }

    @PutMapping(value = "/restart",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public MachineStatus restartMachine(@RequestParam Long machineId, @RequestHeader(name = "Authorization") String token) throws UnsupportedEncodingException, InterruptedException {
        return machineService.restartMachine(machineId, token);
    }

    @PostMapping(value = "/create",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Machine createMachine(@RequestParam String machineName, @RequestHeader(name = "Authorization") String token) throws UnsupportedEncodingException {
        return machineService.createMachine(machineName, token);
    }

    @PutMapping(value = "/destroy",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void destroyMachine(@RequestParam Long machineId, @RequestHeader(name = "Authorization") String token) throws UnsupportedEncodingException, InterruptedException {
        machineService.destroyMachine(machineId, token);
    }

    @PostMapping(value = "/search",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Machine> searchMachine(@RequestParam(required = false) String machineName, @RequestParam(required = false) List<String> status, @RequestParam(required = false) Date dateFrom, @RequestParam(required = false) Date dateTo, @RequestHeader(name = "Authorization") String token) throws UnsupportedEncodingException {
        return machineService.searchMachines(machineName, status, dateFrom, dateTo, token);
    }


}
