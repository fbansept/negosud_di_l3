package edu.ban7.negosud_di_l3.mock;

import edu.ban7.negosud_di_l3.dao.UtilisateurDao;
import edu.ban7.negosud_di_l3.model.Utilisateur;
import edu.ban7.negosud_di_l3.security.Role;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MockUtilisateurDao implements UtilisateurDao {
    @Override
    public Optional<Utilisateur> findByEmail(String email) {
        if(email.equals("a@a.com")) {
            return Optional.of(
                    new Utilisateur(
                            1,
                            "a@a.com",
                            "dvq35v35fq4v35fq1v35f1",
                            Role.ADMIN));
        }
        return Optional.empty();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Utilisateur> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Utilisateur> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Utilisateur> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Utilisateur getOne(Integer integer) {
        return null;
    }

    @Override
    public Utilisateur getById(Integer integer) {
        return null;
    }

    @Override
    public Utilisateur getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Utilisateur> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Utilisateur> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Utilisateur> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Utilisateur> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Utilisateur> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Utilisateur> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Utilisateur, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Utilisateur> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Utilisateur> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Utilisateur> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Utilisateur> findAll() {
        return List.of();
    }

    @Override
    public List<Utilisateur> findAllById(Iterable<Integer> integers) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Utilisateur entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Utilisateur> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Utilisateur> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Utilisateur> findAll(Pageable pageable) {
        return null;
    }
}
