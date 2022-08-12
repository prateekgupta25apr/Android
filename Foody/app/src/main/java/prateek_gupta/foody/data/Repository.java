package prateek_gupta.foody.data;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ActivityRetainedScoped;

@ActivityRetainedScoped
public class Repository {
    RemoteDataSource remote;

    @Inject
    public Repository(RemoteDataSource remoteDataSource) {
        remote = remoteDataSource;
    }

    public RemoteDataSource getRemote() {
        return remote;
    }
}
