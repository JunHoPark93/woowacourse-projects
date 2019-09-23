package webserver.view;

public interface ViewResolver {

    /**
     * path 에 따라 맞는 폴더를 prefix 에 붙여준다.
     *
     * @param path 요청 path
     * @return prefix 를 포함한 전체 path
     */
    String resolve(String path);
}
