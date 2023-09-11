class GetInfoHome {
  public static async getData(idUser: string) {
    const response = await import(
      "../mocks/infoHomeUser/profe_" + idUser + ".json"
    );
    return response.data;
  }
}

export default GetInfoHome;
