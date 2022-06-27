package spacexdemo.domain

import spacexdemo.domain.api.Repo
import spacexdemo.domain.dto.CompanyInfo

class CompanyInfoUseCase(
    val specexCompanyInfoRepo: Repo<CompanyInfo>
) {
    suspend fun getCompanyInfo(): CompanyInfo = specexCompanyInfoRepo.get()
}