package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.RechargeDTO;
import com.stefanogiuseppe.carsharing.dto.UserDTO;
import com.stefanogiuseppe.carsharing.entity.RechargeEntity;
import com.stefanogiuseppe.carsharing.entity.UserEntity;
import com.stefanogiuseppe.carsharing.mapper.RechargeMapper;
import com.stefanogiuseppe.carsharing.service.RechargeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/recharge")
@CrossOrigin(origins = "*")
public class RechargeController {
    @Autowired
    private RechargeService rechargeService;
   /* private ModelMapper modelMapper;

    @Autowired
    public RechargeController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }*/

    @Autowired
    private RechargeMapper rechargeMapper;

    @PostMapping("")
    @ResponseBody
    public RechargeDTO addRecharge(@RequestBody RechargeDTO rechargeDTO) {
        RechargeEntity rechargeEntity = rechargeMapper.toEntity(rechargeDTO);
        RechargeEntity rechargeEntity1 = rechargeService.saveRecharge(rechargeEntity);
        RechargeDTO rechargeDTO1 = rechargeMapper.toDto(rechargeEntity1);
        return rechargeDTO1;
    }

    @GetMapping("")
    @ResponseBody
    public List<RechargeDTO> getAllRecharge() {
        List<RechargeEntity> rechargeEntities = rechargeService.getAllRecharge();
        List<RechargeDTO> rechargeDTO = new ArrayList<>();
        for (RechargeEntity r : rechargeEntities) {
            RechargeDTO rechargeDTO1 = rechargeMapper.toDto(r);
            rechargeDTO.add(rechargeDTO1);
        }
        return rechargeDTO;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public RechargeDTO getRechargeById(@PathVariable Long id) {
        RechargeEntity rechargeEntity = rechargeService.findById(id);
        RechargeDTO rechargeDTO = rechargeMapper.toDto(rechargeEntity);
        return rechargeDTO;
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public RechargeDTO updatePatchRecharge(@PathVariable Long id, @RequestBody RechargeDTO rechargeDTO) {

        RechargeEntity rechargeEntity = rechargeService.updateRecharge(id, rechargeDTO);

        RechargeDTO rechargeDTO1 = rechargeMapper.toDto(rechargeEntity);

        return rechargeDTO1;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public RechargeDTO updatePutRecharge(@PathVariable Long id, @RequestBody RechargeDTO rechargeDTO) {

        RechargeEntity rechargeEntity = rechargeService.updateRecharge(id, rechargeDTO);

        RechargeDTO rechargeDTO1 = rechargeMapper.toDto(rechargeEntity);

        return rechargeDTO1;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteRecharge(@PathVariable Long id) {
        rechargeService.deleteRecharge(id);
        System.out.println("Recharge " + id + " deleted");
    }
}
