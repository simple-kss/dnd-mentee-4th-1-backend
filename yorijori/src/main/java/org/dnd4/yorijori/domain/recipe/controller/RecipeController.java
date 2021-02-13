package org.dnd4.yorijori.domain.recipe.controller;

import lombok.RequiredArgsConstructor;
import org.dnd4.yorijori.domain.common.Result;
import org.dnd4.yorijori.domain.recipe.dto.RequestDto;
import org.dnd4.yorijori.domain.recipe.dto.ResponseDto;
import org.dnd4.yorijori.domain.recipe.dto.UpdateRequestDto;
import org.dnd4.yorijori.domain.recipe.entity.Recipe;
import org.dnd4.yorijori.domain.recipe.service.RecipeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/{id}")
    public Result getById (@PathVariable Long id){

        Recipe recipe = recipeService.get(id);

        ResponseDto responseDto = new ResponseDto(recipe);

        return new Result(responseDto);
    }

    @PutMapping("/{id}")
    public Result update (@PathVariable Long id, @RequestBody @Validated UpdateRequestDto reqDto){

        recipeService.update(id, reqDto);
        return new Result(new ResponseDto(recipeService.get(id)));
    }
}
