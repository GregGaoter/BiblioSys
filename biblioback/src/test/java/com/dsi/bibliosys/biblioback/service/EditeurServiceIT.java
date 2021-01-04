package com.dsi.bibliosys.biblioback.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityNotFoundException;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dsi.bibliosys.biblioback.TestCase;
import com.dsi.bibliosys.biblioback.data.entity.Editeur;
import com.dsi.bibliosys.biblioback.repository.EditeurRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Tag(TestCase.INTEGRATION)
public class EditeurServiceIT {

	private EditeurService editeurService;

	@Autowired
	private EditeurRepository editeurRepository;

	@BeforeEach
	public void setUpBeforeEach() {
		editeurService = new EditeurService(editeurRepository);
	}

	@AfterEach
	public void unDefAfterEach() {
		editeurService = null;
	}

	@Test
	public void getRepository() {
		final JpaRepository<Editeur, Integer> repository = editeurService.getRepository();

		assertThat(repository).isEqualTo(editeurRepository);
	}

	@Test
	public void create() {
		final Editeur editeur = editeurService.create();

		assertThat(editeur).hasAllNullFieldsOrProperties();
	}

	@Test
	public void save() {
		final Editeur editeurExcpected = new Editeur();
		editeurExcpected.setNom("Nom");

		final Editeur editeurSaved = editeurService.save(editeurExcpected);
		final Editeur editeurFind = editeurService.findById(editeurSaved.getId());

		assertThat(editeurFind.getNom()).isEqualTo(editeurExcpected.getNom());
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> editeurService.save(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Editeur editeurSource = new Editeur();
		editeurSource.setNom("Nom");

		final Integer idTarget = 1;

		final Editeur editeurUpdated = editeurService.update(editeurSource, idTarget);

		assertThat(editeurUpdated).extracting(Editeur::getId, Editeur::getNom).containsExactly(editeurUpdated.getId(),
				editeurSource.getNom());
	}

	private static Stream<Arguments> update_null() {
		return Stream.of(Arguments.of(null, 1, "entitySource"), Arguments.of(new Editeur(), null, "id"),
				Arguments.of(null, null, "entitySource"));
	}

	@ParameterizedTest
	@MethodSource
	public void update_null(Editeur source, Integer id, String message) {
		final Exception exception = assertThrows(NullPointerException.class, () -> editeurService.update(source, id));

		assertThat(exception.getMessage().contains(message)).isTrue();
	}

	@Test
	public void delete() {
		final Integer id = 1;
		final Editeur editeur = editeurService.findById(id);

		editeurService.delete(editeur);

		assertThrows(EntityNotFoundException.class, () -> editeurService.findById(id));
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> editeurService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		editeurService.deleteById(id);

		assertThrows(EntityNotFoundException.class, () -> editeurService.findById(id));
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> editeurService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 3L;

		final long nbActual = editeurService.count();

		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer editeurId = 1;

		final Editeur editeurActual = editeurService.findById(editeurId);

		assertThat(editeurActual).extracting(Editeur::getId, Editeur::getNom).containsExactly(editeurId, "Editeur 1");
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> editeurService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Tuple tupleEditeur1 = tuple(1, "Editeur 1");
		final Tuple tupleEditeur2 = tuple(2, "Editeur 2");
		final Tuple tupleEditeur3 = tuple(3, "Editeur 3");

		final List<Editeur> editeursActual = editeurService.findAll();

		assertThat(editeursActual).extracting(Editeur::getId, Editeur::getNom).containsOnly(tupleEditeur1,
				tupleEditeur2, tupleEditeur3);
	}

}
