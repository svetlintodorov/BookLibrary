export const userService = {
    login,
    logout,
    register
};

function login(username, password) {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    };

    return fetch('http://localhost:8080/api/login', requestOptions)
        .then(response => {
            if (response.status != 200) {
                console.log('response is not ok ' + response)
                return Promise.reject(response.status);
            }

            return response;
        })
        .then(user => {
            // login successful if there's a token in the response
            // TODO change this check for token
            console.log('USERNAME' + user.username);
            if (user) {
                console.log('LOCAL STORAGE ')
                // store user details and token in local storage to keep user logged in between page refreshes
                localStorage.setItem('user', JSON.stringify(user));
            }

            return user;
        });
}

function logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('user');
}

function register(user) {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(user)
    };

    return fetch('http://localhost:8080/api/register', requestOptions).then(handleResponse);
}

function handleResponse(response) {
    if (!response.ok) {
        console.log('RESPONSE is not ok'+ response.status)
        return Promise.reject(response.status);
    }

    return response;
}