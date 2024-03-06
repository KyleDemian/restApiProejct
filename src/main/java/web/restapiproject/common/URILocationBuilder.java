package web.restapiproject.common;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class URILocationBuilder {

    private final String path;
    private final Long id;

    public URILocationBuilder(String path, Long id) {
        this.path = path;
        this.id = id;
    }

    public static URILocationBuilder fromId(Long id) {
        return new URILocationBuilder("/{id}", id);
    }

    public URI buildURI(){
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(path)
                .buildAndExpand(id)
                .toUri();
    }
}
