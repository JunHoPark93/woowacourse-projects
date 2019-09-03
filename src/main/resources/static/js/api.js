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
    }
};