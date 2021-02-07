package org.dnd4.yorijori.domain.label.repository;

import org.dnd4.yorijori.domain.label.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
}
