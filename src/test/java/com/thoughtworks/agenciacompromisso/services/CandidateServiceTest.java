package com.thoughtworks.agenciacompromisso.services;

import com.thoughtworks.agenciacompromisso.exceptions.CandidateNotFoundException;
import com.thoughtworks.agenciacompromisso.models.Candidate;
import com.thoughtworks.agenciacompromisso.models.Sizes;
import com.thoughtworks.agenciacompromisso.models.enums.GenderExpression;
import com.thoughtworks.agenciacompromisso.repositories.CandidateRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CandidateServiceTest {

    private Candidate candidate;

    @MockBean
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateService candidateService;

    @BeforeEach
    public void setUp() {
        candidate = new Candidate();
        candidate.setName("Maria");
        candidate.setPhoneNumber("51999111111");
        candidate.setGenderExpression(GenderExpression.FEMALE);
        candidate.setSizes(new Sizes(108.0, 87.0, 100.0, 160.0, "M", 42, 40));
        candidate.setBirthday(LocalDate.of(1990, 2, 11));
    }

    @AfterEach
    public void tearDown() {
        reset(candidateRepository);
    }

    @Test
    public void shouldCreateCandidate() {
        Candidate candidateReturned = new Candidate();
        candidateReturned.setId(new ObjectId().toString());
        candidateReturned.setName(candidate.getName());

        when(candidateRepository.save(any(Candidate.class))).thenReturn(candidateReturned);

        Candidate createdCandidate = candidateService.create(candidate);
        assertThat(createdCandidate.getName(), is(candidateReturned.getName()));
        assertThat(createdCandidate.getId(), is(candidateReturned.getId()));

    }

    @Test
    public void shouldReturnAllCandidates() {
        List<Candidate> candidateList = new ArrayList<>();
        Candidate secondCandidate = new Candidate();
        secondCandidate.setName("João");

        candidateList.add(candidate);
        candidateList.add(secondCandidate);

        when(candidateRepository.findAll()).thenReturn(candidateList);

        List<Candidate> candidateListReturned = candidateService.getAll();
        assertThat(candidateListReturned.size(), is(candidateList.size()));
        assertThat(candidateListReturned.get(1).getName(), is("João"));
    }

    @Test
    public void shouldGetCandidate() {

        ObjectId id = new ObjectId();
        candidate.setId(id.toString());

        when(candidateRepository.findById(id)).thenReturn(candidate);
        Candidate candidateReturned = candidateService.get(id);

        assertThat(candidateReturned, is(candidate));

    }

    @Test
    public void shouldFindAllPagesOfCandidate() {
        Page<Candidate> page = new PageImpl<>(Collections.singletonList(candidate));
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        Pageable capture = pageableCaptor.capture();

        when(candidateRepository.findAll(capture)).thenReturn(page);

        Page<Candidate> candidatePageReturned = candidateService.findAllPage(capture);
        assertThat(candidatePageReturned, is(page));
    }

    @Test
    public void shouldUpdateCandidate() {
        ObjectId id = new ObjectId();
        candidate.setId(id.toString());

        Candidate updatedCandidate = new Candidate();

        BeanUtils.copyProperties(candidate, updatedCandidate);
        updatedCandidate.setName("Ana Carolina");
        updatedCandidate.setPhoneNumber("58993249582");

        when(candidateRepository.save(any())).thenReturn(updatedCandidate);

        Candidate candidateReturned = candidateService.update(updatedCandidate, id);

        assertThat(candidateReturned.getName(), is(updatedCandidate.getName()));
        assertThat(candidateReturned.getPhoneNumber(), is(updatedCandidate.getPhoneNumber()));
        assertThat(candidateReturned.getId(), is(updatedCandidate.getId()));
    }

    @Test
    public void searchCandidateByName() {
        Page<Candidate> page = new PageImpl<>(Collections.singletonList(this.candidate));

        String name = "Name";

        Candidate candidate = new Candidate();
        candidate.setId("1");
        candidate.setName(name);

        when(candidateRepository.findByName(any(), any())).thenReturn(page);

        Page<Candidate> results = candidateService.search(any(), any());

        verify(candidateRepository).findByName(any(), any());

        assertThat(results, is(page));
    }
    @Test
    public void shouldDeleteCandidate() {
        String id = "id";

        when(candidateRepository.findById(id)).thenReturn(Optional.of(candidate));
        doNothing().when(candidateRepository).delete(candidate);

        candidateService.delete(id);

        verify(candidateRepository).findById(id);
        verify(candidateRepository).delete(candidate);
    }

    @Test
    public void shouldThrowExceptionWhenTryingToDeleteCandidateThatDoesNotExist() {
        String id = "id";

        when(candidateRepository.findById(id)).thenReturn(Optional.empty());
        doNothing().when(candidateRepository).delete(candidate);

        assertThrows(CandidateNotFoundException.class, () -> candidateService.delete(id));

        verify(candidateRepository).findById(id);
        verify(candidateRepository, never()).delete(candidate);
    }

}
