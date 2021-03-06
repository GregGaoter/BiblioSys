package com.dsi.bibliosys.biblioback.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import com.dsi.bibliosys.biblioback.TestCase;
import com.dsi.bibliosys.biblioback.data.entity.Usager;
import com.dsi.bibliosys.biblioback.service.UsagerService;

@ExtendWith(SpringExtension.class)
@WebFluxTest(UsagerController.class)
@Tag(TestCase.UNIT)
@Disabled
public class UsagerControllerTest {

	@Autowired
	private WebTestClient wtc;

	@MockBean
	private UsagerService usagerService;

	private static Usager usagerGiven;

	@BeforeEach
	public void setUp() {
		usagerGiven = new Usager();
	}

	@AfterEach
	public void unDef() {
		usagerGiven = null;
	}

	// =====================================
	// --- GET
	// =====================================

	@Test
	public void read_returnUsager() {
		given(usagerService.create()).willReturn(usagerGiven);

		ResponseSpec rs = wtc.get().uri("/usager").exchange();

		verify(usagerService).create();
		rs.expectStatus().isOk();
		rs.expectHeader().contentType(MediaType.APPLICATION_JSON);
		rs.expectBody(Usager.class).isEqualTo(usagerGiven);
	}

	private static Stream<Arguments> readId_usagerGiven_returnHttpStatusAndUsager() {
		return Stream.of(Arguments.of(null, HttpStatus.NOT_FOUND), Arguments.of(new Usager(), HttpStatus.OK));
	}

	@ParameterizedTest
	@MethodSource
	public void readId_usagerGiven_returnHttpStatusAndUsager(Usager usager, HttpStatus httpStatus) {
		Integer id = 1;
		given(usagerService.findById(anyInt())).willReturn(usager);

		ResponseSpec rs = wtc.get().uri("/usager/" + id).exchange();

		verify(usagerService).findById(id);
		rs.expectStatus().isEqualTo(httpStatus);
		rs.expectBody(Usager.class).isEqualTo(usager);
	}

	private static Stream<List<Usager>> readAll_usagersNullEmpty_returnNotFound() {
		return Stream.of(null, new ArrayList<Usager>());
	}

	@ParameterizedTest
	@MethodSource
	public void readAll_usagersNullEmpty_returnNotFound(List<Usager> usagers) {
		given(usagerService.findAll()).willReturn(usagers);

		ResponseSpec rs = wtc.get().uri("/usager/all").exchange();

		verify(usagerService).findAll();
		rs.expectStatus().isNotFound();
		rs.expectBody().isEmpty();
	}

	@Test
	public void readAll_usagers_returnOkAndUsagers() {
		List<Usager> usagers = List.of(new Usager(), new Usager());
		given(usagerService.findAll()).willReturn(usagers);

		ResponseSpec rs = wtc.get().uri("/usager/all").exchange();

		verify(usagerService).findAll();
		rs.expectStatus().isOk();
		rs.expectBodyList(Usager.class).hasSize(2).isEqualTo(usagers);
	}

	// =====================================
	// --- POST
	// =====================================

	private static Stream<Arguments> create_usagerGiven_returnHttpStatus() {
		return Stream.of(Arguments.of(null, HttpStatus.NO_CONTENT), Arguments.of(1, HttpStatus.CREATED));
	}

	@ParameterizedTest
	@MethodSource
	public void create_usagerGiven_returnHttpStatus(Integer id, HttpStatus httpStatus) {
		ReflectionTestUtils.setField(usagerGiven, Usager.ID, id);
		given(usagerService.save(any(Usager.class))).willReturn(usagerGiven);

		ResponseSpec rs = wtc.post().uri("/usager").bodyValue(usagerGiven).exchange();

		verify(usagerService).save(usagerGiven);
		rs.expectStatus().isEqualTo(httpStatus);
	}

	// =====================================
	// --- PUT
	// =====================================

	private static Stream<Arguments> update_usagerTarget_returnStatusCode() {
		Usager usagerTarget = new Usager();
		ReflectionTestUtils.setField(usagerTarget, Usager.ID, 1);
		return Stream.of(Arguments.of(null, HttpStatus.NO_CONTENT), Arguments.of(new Usager(), HttpStatus.NO_CONTENT),
				Arguments.of(usagerTarget, HttpStatus.OK));
	}

	@ParameterizedTest
	@MethodSource
	public void update_usagerTarget_returnStatusCode(Usager usagerTarget, HttpStatus httpStatus) {
		given(usagerService.update(any(Usager.class), anyInt())).willReturn(usagerTarget);

		ResponseSpec rs = wtc.put().uri("/usager/1").bodyValue(usagerGiven).exchange();

		verify(usagerService).update(any(Usager.class), anyInt());
		rs.expectStatus().isEqualTo(httpStatus);
		rs.expectBody().isEmpty();
	}

	// =====================================
	// --- DELETE
	// =====================================

	@Test
	public void delete_usager_returnStatusCode() {
		ResponseSpec rs = wtc.delete().uri("/usager/1").exchange();

		verify(usagerService).deleteById(anyInt());
		rs.expectStatus().isOk();
		rs.expectBody().isEmpty();
	}

}
