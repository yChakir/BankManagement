package ma.tiwtiw.bank.controller;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import ma.tiwtiw.bank.dto.UpdateRight;
import ma.tiwtiw.bank.entity.Right;
import ma.tiwtiw.bank.pojo.RightEnum;
import ma.tiwtiw.bank.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/rights")
public class RightController {

  private RightService rightService;

  @Autowired
  public void setRightService(RightService rightService) {
    this.rightService = rightService;
  }

  @GetMapping
  public ResponseEntity<List<Right>> findAll() {
    List<Right> result = rightService.findAll();

    return ResponseEntity.ok(result);
  }

  @GetMapping("own")
  public ResponseEntity<List<Right>> findAll(Principal principal) {
    List<Right> result = rightService.findAll(principal.getName());

    return ResponseEntity.ok(result);
  }

  @PutMapping("{rightEnum}")
  public ResponseEntity update(
      @PathVariable RightEnum rightEnum,
      @RequestBody @Valid UpdateRight updateRight
  ) {
    rightService.update(rightEnum, updateRight.getDescription());

    return ResponseEntity.noContent().build();
  }

  @PutMapping("{rightEnum}/activate")
  public ResponseEntity activate(@PathVariable RightEnum rightEnum) {
    rightService.activate(rightEnum);

    return ResponseEntity.noContent().build();
  }

  @PutMapping("{rightEnum}/deactivate")
  public ResponseEntity deactivate(@PathVariable RightEnum rightEnum) {
    rightService.deactivate(rightEnum);

    return ResponseEntity.noContent().build();
  }
}
