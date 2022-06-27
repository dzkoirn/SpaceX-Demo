package storage

import network.api.SpacexApiService
import spacexdemo.domain.dto.CompanyInfo
import spacexdemo.domain.dto.Launch

class LaunchesRepo(
    spacexApiService: SpacexApiService
) : CachedRepo<List<Launch>>({ spacexApiService.all() })

class CompanyInfoRepo(
    spacexApiService: SpacexApiService
) : CachedRepo<CompanyInfo>({ spacexApiService.company() })