package prateek_gupta.foody.data;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ActivityRetainedScoped;

@ActivityRetainedScoped
public class Repository {
    RemoteDataSource remote;
    LocalDataSource local;

    @Inject
    public Repository(RemoteDataSource remoteDataSource,LocalDataSource localDataSource) {
        remote = remoteDataSource;
        local=localDataSource;
    }

    public RemoteDataSource getRemote() {
        return remote;
    }

    public LocalDataSource getLocal() {
        return local;
    }
}
