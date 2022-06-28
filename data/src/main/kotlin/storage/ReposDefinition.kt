package storage

import network.api.SpacexApiService
import spacexdemo.domain.dto.CompanyInfo
import spacexdemo.domain.dto.Launch
import spacexdemo.domain.dto.Rocket

class LaunchesRepo(
    spacexApiService: SpacexApiService
) : CachedRepo<List<Launch>>({ spacexApiService.all() })

class CompanyInfoRepo(
    spacexApiService: SpacexApiService
) : CachedRepo<CompanyInfo>({ spacexApiService.company() })

class RocketsRepo(
    spacexApiService: SpacexApiService
) : CachedRepo<List<Rocket>>({ spacexApiService.rockets() })