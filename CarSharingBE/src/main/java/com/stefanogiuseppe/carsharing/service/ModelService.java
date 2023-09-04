package com.stefanogiuseppe.carsharing.service;

import com.stefanogiuseppe.carsharing.dto.ModelDTO;
import com.stefanogiuseppe.carsharing.entity.ModelEntity;
import com.stefanogiuseppe.carsharing.repository.ModelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.beans.FeatureDescriptor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Value("${models.file.upload.dir}") // Configura questa property nel tuo application.properties o application.yml
    private String uploadDir; // Percorso della cartella di caricamento delle immagini



    public ModelEntity saveModel(ModelEntity modelEntity) {
        return modelRepository.save(modelEntity);
    }

    public List<ModelEntity> getAllModel() {
        return modelRepository.findAll();
    }

    public ModelEntity findById(Long id) {
        return modelRepository.findById(id).orElseThrow();
    }

    public ModelEntity updateModel(Long id, ModelDTO modelDTO) {
        ModelEntity modelEntity = findById(id);
        modelDTO.setId(modelEntity.getId());

        BeanUtils.copyProperties(modelDTO, modelEntity, getNullPropertyNames(modelDTO));
        return modelRepository.save(modelEntity);

    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public void deleteModel(Long id) {
        ModelEntity modelEntity = modelRepository.findById(id).orElseThrow();
        modelEntity.setDeleted(true);
        modelRepository.save(modelEntity);
    }
    public String uploadImageToModel(Long modelId, MultipartFile file){
        try {
            ModelEntity vehicleModel = findById(modelId);
            if (vehicleModel == null) {
                return "Model not found";
            }
            String fileName = UUID.randomUUID().toString() + ".png";
            String filePath = Paths.get(uploadDir, fileName).toString();
            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            String imageUrl = fileName;
            vehicleModel.setImage(imageUrl);
            saveModel(vehicleModel);
            return imageUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return "Bad request";
        }
    }

}
