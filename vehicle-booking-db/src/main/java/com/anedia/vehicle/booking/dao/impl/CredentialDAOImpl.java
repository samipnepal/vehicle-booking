package com.anedia.vehicle.booking.dao.impl;

import com.anedia.vehicle.booking.core.AbstractBaseDAO;
import com.anedia.vehicle.booking.core.CustomQueryBuild;
import com.anedia.vehicle.booking.dao.interfaces.CredentialDAO;
import com.anedia.vehicle.booking.entity.CredentialBean;

import java.util.List;

public class CredentialDAOImpl extends AbstractBaseDAO<String, CredentialBean> implements CredentialDAO {

    public void createCredential(CredentialBean credentialBean) {
        add(credentialBean);
    }

    public void updateCredential(CredentialBean credentialBean) {
        update(credentialBean);
    }

    public int removeCredential(List<CustomQueryBuild> CustomQueryBuildList) {
        return 0;
    }

    public CredentialBean findCredential(CustomQueryBuild customQueryBuild) {
        return null;
    }

    public List<CredentialBean> findCredentialList(CustomQueryBuild customQueryBuild) {
        return null;
    }

    protected Class<CredentialBean> getPersistentClass() {
        return CredentialBean.class;
    }
}
