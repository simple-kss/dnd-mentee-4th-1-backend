package org.dnd4.yorijori.domain.label.service;

import org.dnd4.yorijori.domain.label.repository.LabelRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LabelService {

	private final LabelRepository labelRepository;
	
}
