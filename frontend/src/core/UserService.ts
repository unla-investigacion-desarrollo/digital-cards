class UserService {
  public static loginRequest(username: string, password: string) {
    //axios.post(`/api/auth`, { username, password });

    localStorage.setItem("username", username);
    return { username, password };
  }
}

export default UserService;
