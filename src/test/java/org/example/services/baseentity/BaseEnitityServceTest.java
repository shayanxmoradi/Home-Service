package org.example.services.baseentity;

import org.example.entites.BaseEntity;
import org.example.repositories.baseentity.BaseEnitityRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BaseEnitityServceTest {

    @Mock
    private BaseEnitityRepo<BaseEntity<Long>, Long> baseRepository;

    @InjectMocks
    private BaseEntityServceImpl<BaseEntity<Long>, Long, BaseEnitityRepo<BaseEntity<Long>, Long>> baseEntityServce;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        BaseEntity<Long> entity = new BaseEntity<>();
        when(baseRepository.save(entity)).thenReturn(Optional.of(entity));

        Optional<BaseEntity<Long>> result = baseEntityServce.save(entity);

        verify(baseRepository, times(1)).save(entity);
        assertTrue(result.isPresent());
        assertEquals(entity, result.get());
    }

    @Test
    void testUpdate() {
        BaseEntity<Long> entity = new BaseEntity<>();
        when(baseRepository.update(entity)).thenReturn(Optional.of(entity));

        Optional<BaseEntity<Long>> result = baseEntityServce.update(entity);

        verify(baseRepository, times(1)).update(entity);
        assertTrue(result.isPresent());
        assertEquals(entity, result.get());
    }

    @Test
    void testDelete() {
        BaseEntity<Long> entity = new BaseEntity<>();
        when(baseRepository.delelte(entity)).thenReturn(true);

        boolean result = baseEntityServce.delete(entity);

        verify(baseRepository, times(1)).delelte(entity);
        assertTrue(result);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        when(baseRepository.deleteByID(id)).thenReturn(true);

        boolean result = baseEntityServce.deleteById(id);

        verify(baseRepository, times(1)).deleteByID(id);
        assertTrue(result);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        BaseEntity<Long> entity = new BaseEntity<>();
        when(baseRepository.findById(id)).thenReturn(Optional.of(entity));

        Optional<BaseEntity<Long>> result = baseEntityServce.findById(id);

        verify(baseRepository, times(1)).findById(id);
        assertTrue(result.isPresent());
        assertEquals(entity, result.get());
    }

    @Test
    void testFindAll() {
        when(baseRepository.findAll()).thenReturn(Optional.of(List.of(new BaseEntity<>())));

        Optional<List<BaseEntity<Long>>> result = baseEntityServce.findAll();

        verify(baseRepository, times(1)).findAll();
        assertTrue(result.isPresent());
        assertFalse(result.get().isEmpty());
    }

//    @Test
//    void testFindByAttribute() {
//        String attributeName = "name";
//        Object attributeValue = "test";
//        when(baseRepository.findWithAttribute(BaseEntity.class, attributeName, attributeValue))
//                .thenReturn(Optional.of(List.of(new BaseEntity<>())));
//
//        Optional<List<BaseEntity<Long>>> result = baseEntityServce.findByAttribute(BaseEntity.class, attributeName, attributeValue);
//
//        verify(baseRepository, times(1)).findWithAttribute(BaseEntity.class, attributeName, attributeValue);
//        assertTrue(result.isPresent());
//        assertFalse(result.get().isEmpty());
//    }
}