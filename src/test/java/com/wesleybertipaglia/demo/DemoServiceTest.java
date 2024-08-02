package com.wesleybertipaglia.demo;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wesleybertipaglia.demo.dtos.DemoCreateDTO;
import com.wesleybertipaglia.demo.dtos.DemoReadDTO;
import com.wesleybertipaglia.demo.dtos.DemoUpdateDTO;
import com.wesleybertipaglia.demo.services.DemoService;

@SpringBootTest
public class DemoServiceTest {
    @Autowired
    private DemoService demoService;

    @Test
    public void testCreateDemoSuccess() throws Exception {
        DemoCreateDTO demoCreateDTO = new DemoCreateDTO("Title", "Description");
        DemoReadDTO result = demoService.createDemo(demoCreateDTO);

        assert result.id() == 1L;
        assert result.title().equals("Title");
        assert result.description().equals("Description");
    }

    @Test
    public void testCreateDemoFailure() throws Exception {
        DemoCreateDTO demoCreateDTO = new DemoCreateDTO("Title", "");

        try {
            demoService.createDemo(demoCreateDTO);
        } catch (Exception e) {
            assert e.getMessage().equals("Description is required");
        }
    }

    @Test
    public void testReadDemoSuccess() throws Exception {
        DemoCreateDTO demoCreateDTO = new DemoCreateDTO("Title", "Description");
        DemoReadDTO demoReadDTO = demoService.createDemo(demoCreateDTO);
        DemoReadDTO result = demoService.getDemo(demoReadDTO.id());

        assert result.id() == 1L;
        assert result.title().equals("Title");
        assert result.description().equals("Description");
    }

    @Test
    public void testReadDemoFailure() throws Exception {
        try {
            demoService.getDemo(1L);
        } catch (Exception e) {
            assert e.getMessage().equals("Demo not found");
        }
    }

    @Test
    public void testUpdateDemoSuccess() throws Exception {
        DemoCreateDTO demoCreateDTO = new DemoCreateDTO("Title", "Description");
        DemoReadDTO demoReadDTO = demoService.createDemo(demoCreateDTO);

        DemoUpdateDTO demoUpdateDTO = new DemoUpdateDTO(Optional.of("New Title"), Optional.of("New Description"));
        DemoReadDTO result = demoService.updateDemo(demoReadDTO.id(), demoUpdateDTO);

        assert result.id() == 1L;
        assert result.title().equals("New Title");
        assert result.description().equals("New Description");
    }

    @Test
    public void testUpdateDemoFailure() throws Exception {
        DemoCreateDTO demoCreateDTO = new DemoCreateDTO("Title", "Description");
        DemoReadDTO demoReadDTO = demoService.createDemo(demoCreateDTO);

        DemoUpdateDTO demoUpdateDTO = new DemoUpdateDTO(Optional.of("New Title"), Optional.of(""));
        try {
            demoService.updateDemo(demoReadDTO.id(), demoUpdateDTO);
        } catch (Exception e) {
            assert e.getMessage().equals("Description is required");
        }
    }

    @Test
    public void testDeleteDemoSuccess() throws Exception {
        DemoCreateDTO demoCreateDTO = new DemoCreateDTO("Title", "Description");
        DemoReadDTO demoReadDTO = demoService.createDemo(demoCreateDTO);

        demoService.deleteDemo(demoReadDTO.id());

        try {
            demoService.getDemo(demoReadDTO.id());
        } catch (Exception e) {
            assert e.getMessage().equals("Demo not found");
        }
    }

    @Test
    public void testDeleteDemoFailure() throws Exception {
        try {
            demoService.deleteDemo(1L);
        } catch (Exception e) {
            assert e.getMessage().equals("Demo not found");
        }
    }
}