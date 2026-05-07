async function addToCart(productId) {
  const quantity = parseInt(document.getElementById("qty-" + productId).value);

  try {
    const userId = await getUserIdFromEmail();

    await axios.post("http://localhost:8097/cart", {
      userId,
      productId,
      quantity
    });

    alert("장바구니에 담았습니다!");
  } catch (err) {
    console.error("장바구니 오류", err);
    alert("장바구니 추가 실패");
  }
}
