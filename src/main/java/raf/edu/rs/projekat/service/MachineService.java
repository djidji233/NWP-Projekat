package raf.edu.rs.projekat.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import raf.edu.rs.projekat.controller.ApiException;
import raf.edu.rs.projekat.model.*;
import raf.edu.rs.projekat.repository.ErrorMessageRepository;
import raf.edu.rs.projekat.repository.MachineRepository;
import raf.edu.rs.projekat.repository.UserRepository;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

@Service
public class MachineService implements IService<Machine, Long> {

    private Logger logger;
    private final MachineRepository machineRepository;
    private final UserRepository userRepository;
    private final ErrorMessageRepository errorMessageRepository;
    private TaskScheduler taskScheduler;
    private DecodedToken token;
    private Semaphore lock;

    @Autowired
    public MachineService(MachineRepository machineRepository, UserRepository userRepository, ErrorMessageRepository errorMessageRepository, TaskScheduler taskScheduler) {
        this.logger = Logger.getLogger(this.getClass().getName());
        this.machineRepository = machineRepository;
        this.userRepository = userRepository;
        this.errorMessageRepository = errorMessageRepository;
        this.taskScheduler = taskScheduler;
        this.token = null;
        this.lock = new Semaphore(1);
    }


    @Override
    public <S extends Machine> S save(S var1, String jwt) {
        return machineRepository.save(var1);
    }

    @Override
    public Optional<Machine> findById(Long var1, String jwt) {
        return machineRepository.findById(var1);
    }

    @Override
    public List<Machine> findAll(String jwt) {
        return machineRepository.findAll();
    }

    @Override
    public void deleteById(Long var1, String jwt) {
        machineRepository.deleteById(var1);
    }

    // START
    //@Scheduled(fixedDelay = 11000)
    @Async
    public MachineStatus startMachine(Long machineId, String jwt) throws InterruptedException, UnsupportedEncodingException {
        logger.info("START MACHINE...");
        try {
            lock.acquire();
            token = DecodedToken.getDecoded(jwt);
            if (token.can_start_machines) {
                if (findById(machineId, jwt).isPresent()) {
                    Machine machine = findById(machineId, jwt).get();
                    if (machine.getStatus().equals(MachineStatus.STOPPED)) {
                        Thread.sleep(10000);
                        machine.setStatus(MachineStatus.RUNNING);
                        machineRepository.save(machine);
                        logger.info("STATUS CHANGED TO: " + machine.getStatus());
                        return machine.getStatus();
                    } else {
                        // NAPRAVITI NOVI MachineException koji ce moci da primi i scheduled=true
//                        saveErrorMessage(new Date(Calendar.getInstance().getTimeInMillis()),
//                                machineId,
//                                "START",
//                                "ERROR AT SCHEDULED START");
                        throw new ApiException(HttpStatus.BAD_REQUEST, "machine status must be STOPPED", null);
                    }
                } else {
                    throw new ApiException(HttpStatus.BAD_REQUEST, "machine with that id doesn't exist", null);
                }
            } else {
                throw new ApiException(HttpStatus.FORBIDDEN, "can_start_machines privilege missing", null);
            }
        } finally {
            lock.release();
        }

    }

    // STOP
    //@Scheduled(fixedDelay = 11000)
    @Async
    public MachineStatus stopMachine(Long machineId, String jwt) throws InterruptedException, UnsupportedEncodingException {
        logger.info("STOP MACHINE...");
        try {
            lock.acquire();
            token = DecodedToken.getDecoded(jwt);
            if (token.can_stop_machines) {
                if (findById(machineId, jwt).isPresent()) {
                    Machine machine = findById(machineId, jwt).get();
                    if (machine.getStatus().equals(MachineStatus.RUNNING)) {
                        Thread.sleep(10000);
                        machine.setStatus(MachineStatus.STOPPED);
                        machineRepository.save(machine);
                        logger.info("STATUS CHANGED TO: " + machine.getStatus());
                        return machine.getStatus();
                    } else {
                        throw new ApiException(HttpStatus.BAD_REQUEST, "machine status must be RUNNING", null);
                    }
                } else {
                    throw new ApiException(HttpStatus.BAD_REQUEST, "machine with that id doesn't exist", null);
                }
            } else {
                throw new ApiException(HttpStatus.FORBIDDEN, "can_stop_machines privilege missing", null);
            }
        } finally {
            lock.release();
        }
    }

    // RESTART
    //@Scheduled(fixedDelay = 11000)
    @Async
    public MachineStatus restartMachine(Long machineId, String jwt) throws InterruptedException, UnsupportedEncodingException {
        logger.info("RESTART MACHINE...");
        try {
            lock.acquire();
            token = DecodedToken.getDecoded(jwt);
            if (token.can_restart_machines) {
                if (findById(machineId, jwt).isPresent()) {
                    Machine machine = findById(machineId, jwt).get();
                    if (machine.getStatus().equals(MachineStatus.RUNNING)) {
                        Thread.sleep(5000);
                        machine.setStatus(MachineStatus.STOPPED);
                        logger.info("STATUS CHANGED TO: " + machine.getStatus());
                        Thread.sleep(5000);
                        machine.setStatus(MachineStatus.RUNNING);
                        logger.info("STATUS CHANGED TO: " + machine.getStatus());
                        return machine.getStatus();
                    } else {
                        throw new ApiException(HttpStatus.BAD_REQUEST, "machine status must be RUNNING", null);
                    }
                } else {
                    throw new ApiException(HttpStatus.BAD_REQUEST, "machine with that id doesn't exist", null);
                }
            } else {
                throw new ApiException(HttpStatus.FORBIDDEN, "can_restart_machines privilege missing", null);
            }
        } finally {
            lock.release();
        }

    }

    // CREATE
    public Machine createMachine(String machineName, String jwt) throws UnsupportedEncodingException {
        logger.info("CREATE MACHINE...");
        token = DecodedToken.getDecoded(jwt);
        if (token.can_create_machines) {
            Machine machine = new Machine();
            machine.setStatus(MachineStatus.STOPPED);
            machine.setName(machineName);
            machine.setCreatedAt(new Date(Calendar.getInstance().getTimeInMillis()));
            machine.setActive(true);
            User u = userRepository.findByUsername(token.sub);
            machine.setCreatedBy(u);
            machineRepository.save(machine);
            // treba dodati i masinu u userove masine ? testirati
//            if(!u.getMachines().contains(machine)){
//
//                u.setMachines(u.getMachines().add(machine));
//            }
            logger.info("MACHINE CREATED");
            return machine;
        } else {
            throw new ApiException(HttpStatus.FORBIDDEN, "can_create_machines privilege missing", null);
        }
    }

    // DESTROY
    public void destroyMachine(Long machineId, String jwt) throws UnsupportedEncodingException {
        logger.info("DESTROY MACHINE...");
        token = DecodedToken.getDecoded(jwt);
        if (token.can_destroy_machines) {
            if (findById(machineId, jwt).isPresent()) {
                Machine machine = findById(machineId, jwt).get();
                if (machine.getStatus().equals(MachineStatus.STOPPED)) {
                    machine.setActive(false); // soft delete
                    machineRepository.save(machine);
                    logger.info("MACHINE DESTROYED");
                } else {
                    throw new ApiException(HttpStatus.BAD_REQUEST, "machine status must be STOPPED", null);
                }
            } else {
                throw new ApiException(HttpStatus.BAD_REQUEST, "machine with that id doesn't exist", null);
            }
        } else {
            throw new ApiException(HttpStatus.FORBIDDEN, "can_destroy_machines privilege missing", null);
        }
    }


    // SEARCH
    public List<Machine> searchMachines(String name, List<String> status, Date dateFrom, Date dateTo, String jwt) throws UnsupportedEncodingException {
        logger.info("SEARCH MACHINES...");
        token = DecodedToken.getDecoded(jwt);
        if (token.can_search_machines) {
            if (name == null && status == null && dateFrom == null && dateTo == null) {
                return machineRepository.findAll();
            } else {
                List<MachineStatus> statusEnum = new ArrayList<>();
                if (status != null) {
                    for (String s : status) {
                        if (s.equalsIgnoreCase("STOPPED")) {
                            statusEnum.add(MachineStatus.STOPPED);
                        }
                        if (s.equalsIgnoreCase("RUNNING")) {
                            statusEnum.add(MachineStatus.RUNNING);
                        }
                    }
                }
                return machineRepository.search(name, statusEnum, dateFrom, dateTo);
            }
        } else {
            throw new ApiException(HttpStatus.FORBIDDEN, "can_search_machines privilege missing", null);
        }
    }

    public void scheduleTask(ScheduleTask task, String jwt) throws UnsupportedEncodingException, InterruptedException {
        //System.out.println(task.dayOfWeek); * * * * * * WORKS
        String cronString = String.format("%s %s %s %s %s %s", task.minute, task.hour, task.dayOfMonth, task.month, task.dayOfWeek, task.year);
        CronTrigger cronTrigger = new CronTrigger(cronString);
        //CronTrigger cronTrigger = new CronTrigger("12 2 * * * *");
//        CronTrigger cronTrigger = new CronTrigger("0 * * * * *"); // WORKS
        // ? Invalid value for DayOfWeek (valid values 1 - 7): 2022
        // ? java.lang.NumberFormatException: For input string: "?"
        // CronTrigger cronTrigger = new CronTrigger(cronExpression);

        // TEST SCHEDULE
        this.taskScheduler.schedule(() -> {
            System.out.println("SCHEDULE TEST");
        }, cronTrigger);


        // SCHEDULE START TASK
        if (task.methodName.equalsIgnoreCase("START")) {
            logger.info("SCHEDULE START MACHINE...");
            this.taskScheduler.schedule(() -> {
                try {
                    startMachine(task.machineId, jwt);
                } catch (InterruptedException | UnsupportedEncodingException e) {

                    e.printStackTrace();
                }
            }, cronTrigger);
        }
        // SCHEDULE STOP TASK
        if (task.methodName.equalsIgnoreCase("STOP")) {
            logger.info("SCHEDULE STOP MACHINE...");
            this.taskScheduler.schedule(() -> {
                try {
                    stopMachine(task.machineId, jwt);
                } catch (InterruptedException | UnsupportedEncodingException e) {

                    e.printStackTrace();
                }
            }, cronTrigger);
        }
        // SCHEDULE RESTART TASK
        if (task.methodName.equalsIgnoreCase("RESTART")) {
            logger.info("SCHEDULE RESTART MACHINE...");
            this.taskScheduler.schedule(() -> {
                try {
                    restartMachine(task.machineId, jwt);
                } catch (InterruptedException | UnsupportedEncodingException e) {
//                    saveErrorMessage(new Date(Calendar.getInstance().getTimeInMillis()),
//                                    task.machineId,
//                                    task.methodName,
//                                    "ERROR AT SCHEDULED RESTART");
                    e.printStackTrace();
                }
            }, cronTrigger);
        }
    }

    private void saveErrorMessage(Date date, Long machineId, String methodName, String msg) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setDate(date);
        errorMessage.setMachineId(machineId);
        errorMessage.setMethodName(methodName);
        errorMessage.setErrorMessage(msg);
        errorMessageRepository.save(errorMessage);
    }

}
