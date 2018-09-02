package pl.javastart.trunki;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrunkiRepository extends JpaRepository<Trunki, Long> {
    //List<Trunki> findByVolumeGreaterThan(double volume);
    //List<Trunki>findAllOrderByVoltageDesc(int volume);
    List<Trunki>findAllByNameStartsWith(String pattern);

    public Trunki findByName(String name);
}
