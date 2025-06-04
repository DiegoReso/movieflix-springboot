package br.com.movieflix.service;

import br.com.movieflix.entity.Streaming;
import br.com.movieflix.repository.StreamingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StreamingServiceTest {

    @InjectMocks
    StreamingService streamingService;

    @Mock
    StreamingRepository streamingRepository;

    @Test
    @DisplayName("Testa se o metodo retorna lista de streamings")
    void shouldReturnAllStreamings() {

        Streaming streaming = new Streaming(1L, "Netfrix");
        Streaming streaming2 = new Streaming(2L, "Praime");

        Mockito.when(streamingRepository.findAll()).thenReturn(List.of(streaming, streaming2));

        List<Streaming> streamings = streamingService.findAll();

        assertEquals(2, streamings.size());

    }

    @Test
    @DisplayName("Testa se o metodo retorna lista vazia quando nao ha streamings")
    void shouldReturnEmptyList(){

        Mockito.when(streamingRepository.findAll()).thenReturn(List.of());
        List<Streaming> streamings = streamingService.findAll();

        assertEquals(0, streamings.size());

    }

    @Test
    void insertStreaming() {
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }
}