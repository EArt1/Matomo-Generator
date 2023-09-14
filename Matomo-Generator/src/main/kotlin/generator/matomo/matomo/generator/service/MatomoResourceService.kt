package generator.matomo.matomo.generator.service

import generator.matomo.matomo.generator.entetys.MatomoResource
import generator.matomo.matomo.generator.entetys.MatomoResourceRepository
import org.springframework.stereotype.Service

@Service
class MatomoResourceService(private val repository: MatomoResourceRepository) {

    fun createResource(resource: MatomoResource): MatomoResource {
        repository.findByNameAndNamespace(resource.name, resource.namespace).ifPresent {
            throw DuplicateResourceException("A resource with the name ${resource.name} in the namespace ${resource.namespace} already exists.")
        }
        return repository.save(resource)
    }

    fun getAllResources(): List<MatomoResource> {
        return repository.findAll()
    }

    class DuplicateResourceException(message: String) : RuntimeException(message)
}
