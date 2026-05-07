async function getUserIdFromEmail() {
  const email = localStorage.getItem("userEmail");
  if (!email) {
    alert("로그인이 필요합니다.");
    throw new Error("로그인 정보가 없습니다.");
  }

  const res = await axios.get("http://223.130.153.51:30909/users/id", {
    params: { email }
  });

  return res.data; // userId
}
