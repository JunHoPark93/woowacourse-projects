const SEARCH_BOX = (function () {
    const Api = function () {
        const request = {
            get(path, params) {
                return axios.get(`${path}`, {params: params});
            },
            post(path, data) {
                return axios.post(`${path}`, data);
            },
            put(path, data) {
                return axios.put(`${path}`, data);
            },
            delete(path) {
                return axios.delete(`${path}`);
            }
        };

        return {
            request: request
        };
    };

    const SearchBoxController = function () {
        const searchService = new SearchService();

        const toggleSearchInput = function () {
            document.querySelector('.search-toggle').addEventListener('click', searchService.toggleSearchInput);
        };

        const showSearchedList = function () {
            document.querySelector(".search-input input").addEventListener('keyup', searchService.showSearchedList);
        };

        const init = function () {
            toggleSearchInput();
            showSearchedList();
        };

        return {
            init: init,
        };
    };

    const SearchService = function () {
        const request = new Api().request;

        const toggleSearchInput = function (event) {
            event.preventDefault();
            document.querySelector('.search-box').classList.toggle('active');
            document.querySelector(".search-input").classList.toggle("active");
            document.querySelector(".search-input input").focus()
        };

        const showSearchedList = function (event) {
            const keyword = event.target.value;

            if (keyword.length > 0) {
                request
                    .get("/api/search/" + keyword, {
                        "maxSizeOfNickName": 5,
                        "maxSizeOfHashtag": 5,
                    })
                    .then(response => {
                        return response.data;
                    })
                    .then(data => {
                        console.log(data);
                    });


                document.querySelector(".advanced-search").classList.add("active")
            } else {
                document.querySelector(".advanced-search").classList.remove("active")
            }
        };

        return {
            toggleSearchInput: toggleSearchInput,
            showSearchedList: showSearchedList,
        }
    };

    const searchBoxController = new SearchBoxController();

    const init = function () {
        searchBoxController.init();
    };

    return {
        init: init,
    }
})();

SEARCH_BOX.init();
