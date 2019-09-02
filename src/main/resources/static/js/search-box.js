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
        const maxSizeOfNickName = 5;
        const maxSizeOfHashtag = 5;
        const request = new Api().request;

        const createMemberSearchedItem = function (nodes) {
            const newNode = document.createElement("LI");
            newNode.classList.add('search-result-member-item');

            newNode.innerHTML = nodes;
            return newNode;
        };

        const createHashtagSearchedItem = function (nodes) {
            const newNode = document.createElement("LI");
            newNode.classList.add('search-result-hashtag-item');

            newNode.innerHTML = nodes;
            return newNode;
        };

        const searchedMemberTemplate = function (json) {
            return `
            <a href="/members/${json.nickName}" class="no-pdd">
                <div class="pdd-vertical-10 pdd-horizon-20 mrg-vertical-5">
                    <img class="thumb-img img-circle" src="${json.profileImage}" alt="">
                    <div class="info">
                            <span class="title lh-1-5">${json.nickName}</span>
                        <span class="sub-title">
                            <span>${json.name}</span>
                        </span>
                    </div>
                </div>
            </a>`
        };

        const searchedHashtagTemplate = function (json) {
            return `
			<a href="/tags/${json.keyword}" class="text-dark no-pdd">
                <div class="pdd-vertical-10 pdd-horizon-20 mrg-vertical-5">
			        # ${json.keyword}
			    </div>
			</a>`
        };

        const toggleSearchInput = function (event) {
            event.preventDefault();
            document.querySelector('.search-box').classList.toggle('active');
            document.querySelector(".search-input").classList.toggle("active");
            document.querySelector(".search-input input").focus()
        };

        const removeAllChild = function (node) {
            while (node.hasChildNodes()) {
                node.removeChild(node.firstChild);
            }
        };

        const showSearchedList = function (event) {
            const target = event.target;
            const keyword = target.value;

            const searchedList = target.closest('LI').querySelector('UL');

            const appendMemberSearchedItem = function (member) {
                const memberNode = createMemberSearchedItem(searchedMemberTemplate(member));
                searchedList.appendChild(memberNode);
            };

            const appendHashtagSearchedItem = function (hashtag) {
                const hashtagNode = createHashtagSearchedItem(searchedHashtagTemplate(hashtag));
                searchedList.appendChild(hashtagNode);
            };

            if (keyword.length > 0) {
                request
                    .get(`/api/search/${keyword}`, {
                        "maxSizeOfNickName": maxSizeOfNickName,
                        "maxSizeOfHashtag": maxSizeOfHashtag,
                    })
                    .then(response => {
                        return response.data;
                    })
                    .then(data => {
                        removeAllChild(searchedList);

                        data.memberResponses.forEach(member => {
                            appendMemberSearchedItem(member);
                        });

                        data.hashtagResponses.forEach(hashtag => {
                            appendHashtagSearchedItem(hashtag);
                        });
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
