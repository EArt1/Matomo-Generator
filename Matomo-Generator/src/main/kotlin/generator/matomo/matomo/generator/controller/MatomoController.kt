package generator.matomo.matomo.generator.controller

import generator.matomo.matomo.generator.entetys.MatomoResource
import generator.matomo.matomo.generator.entetys.MatomoResourceRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus
import generator.matomo.matomo.generator.service.MatomoResourceService.DuplicateResourceException
import generator.matomo.matomo.generator.service.MatomoResourceService


@RestController
@RequestMapping("/matomo")
class MatomoController(private val service: MatomoResourceService) {

    @PostMapping
    fun create(@RequestBody resource: MatomoResource): ResponseEntity<MatomoResource> {
        return try {
            ResponseEntity.ok(service.createResource(resource))
        } catch (e: DuplicateResourceException) {
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
    }

    @GetMapping
    fun getAll(): List<MatomoResource> = service.getAllResources()
}
