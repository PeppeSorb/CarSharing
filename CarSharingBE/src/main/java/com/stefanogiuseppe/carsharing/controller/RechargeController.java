package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.RechargeDTO;
import com.stefanogiuseppe.carsharing.entity.RechargeEntity;
import com.stefanogiuseppe.carsharing.service.RechargeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/recharge")
@CrossOrigin(origins = "*")
public class RechargeController {
    @Autowired
    private RechargeService rechargeService;
    private ModelMapper modelMapper;

    @Autowired
    public RechargeController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    @ResponseBody
    public RechargeDTO addRecharge(@RequestBody RechargeDTO rechargeDTO) {
        RechargeEntity rechargeEntity = modelMapper.map(rechargeDTO, RechargeEntity.class);
        RechargeEntity rechargeEntity1 = rechargeService.saveRecharge(rechargeEntity);
        RechargeDTO rechargeDTO1 = modelMapper.map(rechargeEntity1, RechargeDTO.class);
        return rechargeDTO1;
    }

    @GetMapping("")
    @ResponseBody
    public List<RechargeDTO> getAllRecharge() {
        List<RechargeEntity> rechargeEntities = rechargeService.getAllRecharge();
        List<RechargeDTO> rechargeDTO = rechargeEntities.stream()
                .map(recharge -> modelMapper.map(recharge, RechargeDTO.class))
                .collect(Collectors.toList());
        return rechargeDTO;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public RechargeDTO getRechargeById(@PathVariable Long id) {
        RechargeEntity rechargeEntity = rechargeService.findById(id);
        RechargeDTO rechargeDTO = modelMapper.map(rechargeEntity, RechargeDTO.class);
        return rechargeDTO;
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public RechargeDTO updateRecharge(@PathVariable Long id, @RequestBody RechargeDTO rechargeDTO) {

        RechargeEntity rechargeEntity = rechargeService.updateRecharge(id, rechargeDTO);

        RechargeDTO rechargeDTO1 = modelMapper.map(rechargeEntity, RechargeDTO.class);

        return rechargeDTO1;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteRecharge(@PathVariable Long id) {
        rechargeService.deleteRecharge(id);
        System.out.println("Recharge " + id + " deleted");
    }
}
