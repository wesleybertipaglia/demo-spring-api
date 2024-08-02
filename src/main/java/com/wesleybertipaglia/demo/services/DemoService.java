package com.wesleybertipaglia.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.wesleybertipaglia.demo.dtos.DemoCreateDTO;
import com.wesleybertipaglia.demo.dtos.DemoReadDTO;
import com.wesleybertipaglia.demo.dtos.DemoUpdateDTO;
import com.wesleybertipaglia.demo.entities.Demo;
import com.wesleybertipaglia.demo.repositories.DemoRepository;

import jakarta.persistence.EntityNotFoundException;

import jakarta.validation.Valid;

@Service
public class DemoService {

    @Autowired
    DemoRepository demoRepository;

    @Transactional(readOnly = true)
    public Page<DemoReadDTO> listDemos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return demoRepository.findAll(pageable).map(this::demoToDemoReadDTOMapper);
    }

    @Transactional(readOnly = true)
    public DemoReadDTO getDemo(Long id) {
        Demo demo = demoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Demo not found"));

        return demoToDemoReadDTOMapper(demo);
    }

    @Transactional
    public DemoReadDTO createDemo(@Valid DemoCreateDTO demoCreateDTO) {
        Demo demo = new Demo(demoCreateDTO.title(), demoCreateDTO.description());
        return demoToDemoReadDTOMapper(demoRepository.save(demo));
    }

    @Transactional
    public DemoReadDTO updateDemo(Long id, @Valid DemoUpdateDTO demoUpdateDTO) {
        Demo demo = demoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Demo not found"));

        if (demoUpdateDTO.title().isPresent()) {
            demo.setTitle(demoUpdateDTO.title().get());
        }

        if (demoUpdateDTO.description().isPresent()) {
            demo.setDescription(demoUpdateDTO.description().get());
        }

        return demoToDemoReadDTOMapper(demoRepository.save(demo));
    }

    @Transactional
    public void deleteDemo(Long id) {
        Demo demo = demoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Demo not found"));

        demoRepository.delete(demo);
    }

    private DemoReadDTO demoToDemoReadDTOMapper(Demo demo) {
        return new DemoReadDTO(demo.getId(), demo.getTitle(), demo.getDescription());
    }
}
