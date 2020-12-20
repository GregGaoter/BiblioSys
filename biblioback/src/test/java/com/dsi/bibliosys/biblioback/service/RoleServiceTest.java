package com.dsi.bibliosys.biblioback.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.dsi.bibliosys.biblioback.TestCase;
import com.dsi.bibliosys.biblioback.data.entity.Role;
import com.dsi.bibliosys.biblioback.repository.RoleRepository;

@ExtendWith(SpringExtension.class)
@Tag(TestCase.UNIT)
public class RoleServiceTest {

	private RoleService roleService;

	@Mock
	private RoleRepository roleRepository;

	@BeforeEach
	public void setUpBeforeEach() {
		roleService = new RoleService(roleRepository);
	}

	@AfterEach
	public void unDefAfterEach() {
		roleService = null;
	}

	@Test
	public void getRepository() {
		final JpaRepository<Role, Integer> repository = roleService.getRepository();

		assertThat(repository).isEqualTo(roleRepository);
	}

	@Test
	public void create() {
		final Role role = roleService.create();

		assertThat(role).hasAllNullFieldsOrProperties();
	}

	@Test
	public void save() {
		final Role role = new Role();

		given(roleRepository.saveAndFlush(role)).willAnswer(invocation -> invocation.getArgument(0));

		final Role roleSaved = roleService.save(role);

		verify(roleRepository).saveAndFlush(role);
		assertThat(roleSaved).isNotNull();
	}

	@Test
	public void save_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> roleService.save(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void update() {
		final Role roleSource = new Role();
		roleSource.setNom("nom");

		final Integer idTarget = 1;

		final Optional<Role> roleTargetOptional = Optional.of(new Role());
		ReflectionTestUtils.setField(roleTargetOptional.get(), Role.ID, idTarget);

		given(roleRepository.findById(idTarget)).willReturn(roleTargetOptional);
		given(roleRepository.saveAndFlush(any(Role.class))).willAnswer(invocation -> invocation.getArgument(0));

		final Role roleUpdated = roleService.update(roleSource, idTarget);

		verify(roleRepository).findById(idTarget);
		verify(roleRepository).saveAndFlush(any(Role.class));
		assertThat(roleUpdated.getId()).isEqualTo(idTarget);
		assertThat(roleUpdated).isEqualToIgnoringGivenFields(roleSource, Role.ID);
	}

	private static Stream<Arguments> update_null() {
		return Stream.of(Arguments.of(null, 1, "entitySource"), Arguments.of(new Role(), null, "id"),
				Arguments.of(null, null, "entitySource"));
	}

	@ParameterizedTest
	@MethodSource
	public void update_null(Role source, Integer id, String message) {
		final Exception exception = assertThrows(NullPointerException.class, () -> roleService.update(source, id));

		assertThat(exception.getMessage().contains(message)).isTrue();
	}

	@Test
	public void delete() {
		final Role role = new Role();

		roleService.delete(role);

		verify(roleRepository).delete(role);
	}

	@Test
	public void delete_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> roleService.delete(null));

		assertThat(exception.getMessage().contains("entity")).isTrue();
	}

	@Test
	public void deleteById() {
		final Integer id = 1;

		final Optional<Role> roleOptional = Optional.of(new Role());
		ReflectionTestUtils.setField(roleOptional.get(), Role.ID, id);

		given(roleRepository.findById(id)).willReturn(roleOptional);

		roleService.deleteById(1);

		verify(roleRepository).deleteById(roleOptional.get().getId());
	}

	@Test
	public void deleteById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> roleService.deleteById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void count() {
		final long nbExpected = 1L;

		given(roleRepository.count()).willReturn(nbExpected);

		final long nbActual = roleService.count();

		verify(roleRepository).count();
		assertThat(nbActual).isEqualTo(nbExpected);
	}

	@Test
	public void findById() {
		final Integer id = 1;

		final Optional<Role> roleOptional = Optional.of(new Role());
		ReflectionTestUtils.setField(roleOptional.get(), Role.ID, id);

		given(roleRepository.findById(id)).willReturn(roleOptional);

		final Role roleActual = roleService.findById(id);

		verify(roleRepository).findById(id);
		assertThat(roleActual).isEqualToComparingFieldByField(roleOptional.get());
	}

	@Test
	public void findById_null() {
		final Exception exception = assertThrows(NullPointerException.class, () -> roleService.findById(null));

		assertThat(exception.getMessage().contains("id")).isTrue();
	}

	@Test
	public void findAll() {
		final Role role1 = new Role();
		ReflectionTestUtils.setField(role1, Role.ID, 1);
		final Role role2 = new Role();
		ReflectionTestUtils.setField(role2, Role.ID, 2);
		final Role role3 = new Role();
		ReflectionTestUtils.setField(role3, Role.ID, 3);

		final List<Role> rolesExpected = new ArrayList<>(3);
		rolesExpected.add(role1);
		rolesExpected.add(role2);
		rolesExpected.add(role3);

		given(roleRepository.findAll()).willReturn(rolesExpected);

		final List<Role> rolesActual = roleService.findAll();

		verify(roleRepository).findAll();
		assertThat(rolesActual).containsExactlyInAnyOrderElementsOf(rolesExpected);
	}

}
