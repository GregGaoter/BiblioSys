import axios from "axios";

export const jwtToken = localStorage.getItem("authorization");

axios.interceptors.request.use(
  (config) => {
    if (jwtToken) {
      config.headers["authorization"] = "Bearer " + jwtToken;
    }
    return config;
  },
  (err) => Promise.reject(err)
);
