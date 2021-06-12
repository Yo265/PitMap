package com.example.roadpitmap.repositories;

import com.example.roadpitmap.entities.Pit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository("pitRepository")
public interface PitRepository extends CrudRepository<Pit, Integer> {

    @Query(value = "SELECT AVG(p.velocity) FROM pit p ",
            nativeQuery = true)
    public Float selectVelocityAvg();

    @Query(value = "SELECT MAX(p.velocity) FROM pit p ",
            nativeQuery = true)
    public Float selectVelocityMax();

    @Query(value = "SELECT MIN(p.velocity) FROM pit p ",
            nativeQuery = true)
    public Float selectVelocityMin();


    public Iterable<Pit> streamByDateBefore(String date);
    public Iterable<Pit> streamByDateAfter(String date);
    public Iterable<Pit> streamByDateEquals(String date);

    public Iterable<Pit> streamByIdBefore(Integer id);
    public Iterable<Pit> streamByIdAfter(Integer id);
    public Iterable<Pit> streamByIdEquals(Integer id);

    public Iterable<Pit> streamByLatitudeBefore(Float latitude);
    public Iterable<Pit> streamByLatitudeAfter(Float latitude);
    public Iterable<Pit> streamByLatitudeEquals(Float latitude);

    public Iterable<Pit> streamByLongitudeBefore(Float longitude);
    public Iterable<Pit> streamByLongitudeAfter(Float longitude);
    public Iterable<Pit> streamByLongitudeEquals(Float longitude);

    public Iterable<Pit> streamByVelocityBefore(Float velocity);
    public Iterable<Pit> streamByVelocityAfter(Float velocity);
    public Iterable<Pit> streamByVelocityEquals(Float velocity);
}
