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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    @DisplayName("Testa se o metodo insere uma streaming")
    void insertStreaming() {

        Streaming streaming = new Streaming(1L, "Netfrix");
        Mockito.when(streamingRepository.save(streaming)).thenReturn(streaming);

        Streaming streamingSaved = streamingService.insertStreaming(streaming);

        assertThat(streamingSaved.getName()).isEqualTo("Netfrix");

    }


    @Test
    @DisplayName("Testa se o metodo retorna Streaming por Id")
    void shouldReturnStreamingById() {

        Long streaminId = 1L;
        Streaming streaming = new Streaming(streaminId, "Netfrix");

        Mockito.when(streamingRepository.findById(streaminId)).thenReturn(Optional.of(streaming));

        Optional<Streaming> foundStreaming = streamingService.findById(streaminId);

        assertEquals(streaming, foundStreaming.get());

    }

    @Test
    @DisplayName("Testa se a streaming sera deletada chamando o repository corretamente")
    void shouldCallRepositoryDeleteByIdWhenDeleteStreaming() {

        Long streamingIdToDelete = 1L;

        doNothing().when(streamingRepository).deleteById(streamingIdToDelete);

        streamingService.delete(streamingIdToDelete);

        verify(streamingRepository, times(1)).deleteById(streamingIdToDelete);

    }
}