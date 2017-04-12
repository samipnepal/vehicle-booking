package com.anedia.vehicle.booking.dao.interfaces;

import com.anedia.vehicle.booking.core.CustomQueryBuild;
import com.anedia.vehicle.booking.entity.CredentialBean;

import java.util.List;

public interface CredentialDAO {
    void createCredential(CredentialBean credentialBean);
    void updateCredential(CredentialBean credentialBean);
    int removeCredential(final List<CustomQueryBuild> CustomQueryBuildList);
    CredentialBean findCredential(CustomQueryBuild customQueryBuild);
    List<CredentialBean> findCredentialList(CustomQueryBuild customQueryBuild);
}
