package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.RechargeDTO;
import com.stefanogiuseppe.carsharing.dto.UserDTO;
import com.stefanogiuseppe.carsharing.entity.RechargeEntity;
import com.stefanogiuseppe.carsharing.entity.UserEntity;
import com.stefanogiuseppe.carsharing.mapper.RechargeMapper;
import com.stefanogiuseppe.carsharing.service.RechargeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Recharges Controller", description = "This controller allows create, read, update and delete operations on Recharges")
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
    @Operation(description = "Adds a new recharge to the repository")
    public RechargeDTO addRecharge(@Parameter(description = "The new recharge in a JSON format") @RequestBody RechargeDTO rechargeDTO) {
        RechargeEntity rechargeEntity = rechargeMapper.toEntity(rechargeDTO);
        RechargeEntity rechargeEntity1 = rechargeService.saveRecharge(rechargeEntity);
        RechargeDTO rechargeDTO1 = rechargeMapper.toDto(rechargeEntity1);
        return rechargeDTO1;
    }

    @GetMapping("")
    @ResponseBody
    @Operation(description = "Returns all recharges of the repository")
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
    @Operation(description = "Returns a recharge inside the repository with the specified id")
    public RechargeDTO getRechargeById(@Parameter(description="The id of the requested recharge") @PathVariable Long id) {
        RechargeEntity rechargeEntity = rechargeService.findById(id);
        RechargeDTO rechargeDTO = rechargeMapper.toDto(rechargeEntity);
        return rechargeDTO;
    }

    @PatchMapping("/{id}")
    @ResponseBody
    @Operation(description = "Updates some information of an existing administrator")
    public RechargeDTO updatePatchRecharge(@Parameter(description="The id of the recharge to update") @PathVariable Long id, @Parameter(description = "The updated recharge") @RequestBody RechargeDTO rechargeDTO) {

        RechargeEntity rechargeEntity = rechargeService.updateRecharge(id, rechargeDTO);

        RechargeDTO rechargeDTO1 = rechargeMapper.toDto(rechargeEntity);

        return rechargeDTO1;
    }

    @PutMapping("/{id}")
    @ResponseBody
    @Operation(description = "Updates all information of an existing administrator")
    public RechargeDTO updatePutRecharge(@Parameter(description="The id of the recharge to update") @PathVariable Long id, @Parameter(description = "The updated information of recharge") @RequestBody RechargeDTO rechargeDTO) {

        RechargeEntity rechargeEntity = rechargeService.updateRecharge(id, rechargeDTO);

        RechargeDTO rechargeDTO1 = rechargeMapper.toDto(rechargeEntity);

        return rechargeDTO1;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @Operation(description = "Deletes a recharge from the repository")
    public void deleteRecharge(@Parameter(description = "The id of the recharge to delete") @PathVariable Long id) {
        rechargeService.deleteRecharge(id);
        System.out.println("Recharge " + id + " deleted");
    }
}
