package generator.matomo.matomo.generator.entetys

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository


@Entity
data class MatomoResource(
    @Id @GeneratedValue
    val id: Long? = null,
    val name: String,
    val namespace: String,
    val host: String
)

interface MatomoResourceRepository : JpaRepository<MatomoResource, Long> {
    fun findByNameAndNamespace(name: String, namespace: String): Optional<MatomoResource>
}
