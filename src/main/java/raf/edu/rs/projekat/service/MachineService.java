package raf.edu.rs.projekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raf.edu.rs.projekat.model.Machine;
import raf.edu.rs.projekat.repository.MachineRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MachineService implements IService<Machine, Long> {

    private final MachineRepository machineRepository;

    @Autowired
    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Override
    public <S extends Machine> S save(S var1) {
        return machineRepository.save(var1);
    }

    @Override
    public Optional<Machine> findById(Long var1) {
        return machineRepository.findById(var1);
    }

    @Override
    public List<Machine> findAll() {
        return machineRepository.findAll();
    }

    @Override
    public void deleteById(Long var1) {
        machineRepository.deleteById(var1);
    }

//    public List<Machine> search (String name, String status, Date dateFrom, Date dateTo){
//        List<Machine> res = machineRepository.findAll()
//                                                .stream()
//                                                .filter(machine -> machine.getName().equals(name))
//    }

}
