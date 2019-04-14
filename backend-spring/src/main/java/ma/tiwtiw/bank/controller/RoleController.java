package ma.tiwtiw.bank.controller;

import java.util.List;
import javax.validation.Valid;
import javax.xml.ws.Response;
import ma.tiwtiw.bank.dto.AddRole;
import ma.tiwtiw.bank.dto.EditRole;
import ma.tiwtiw.bank.entity.Role;
import ma.tiwtiw.bank.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {

  private RoleService roleService;

  @Autowired
  public void setRoleService(RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping
  @PreAuthorize("hasAnyAuthority('SHOW_ROLES', 'ALL_RIGHTS')")
  public ResponseEntity<List<Role>> findAll() {
    List<Role> result = roleService.findAll();

    return ResponseEntity.ok(result);
  }

  @PostMapping
  @PreAuthorize("hasAnyAuthority('ADD_ROLE', 'ALL_RIGHTS')")
  public ResponseEntity add(@RequestBody @Valid AddRole addRole) {
    roleService.add(addRole.getName(), addRole.getRights());

    return ResponseEntity.created(null).build();
  }

  @PutMapping("{id}")
  @PreAuthorize("hasAnyAuthority('UPDATE_ROLE', 'ALL_RIGHTS')")
  public ResponseEntity update(
      @PathVariable Long id,
      @RequestBody @Valid EditRole editRole
  ) {
    roleService.update(id,  editRole.getName(), editRole.getRights());

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("{id}")
  @PreAuthorize("hasAnyAuthority('DELETE_ROLE', 'ALL_RIGHTS')")
  public ResponseEntity delete(
      @PathVariable Long id
  ) {
    roleService.delete(id);

    return ResponseEntity.noContent().build();
  }
}
