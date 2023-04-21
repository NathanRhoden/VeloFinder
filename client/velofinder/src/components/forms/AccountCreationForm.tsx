export default function AccountCreationForm() {
  const handleSubmit = (e: any) => {
    e.preventDefault();
    const form = e.target;
    const formData = new FormData(form);

    const formJson = Object.fromEntries(formData.entries());

    fetch("http://localhost:8080/signup", {
      //mode: "no-cors",
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formJson),
    });
    console.log(formJson);
  };

  return (
    <div className="form-body">
      <h1>ACCOUNT REGISTRATION</h1>
      <div>
        <form className="form" onSubmit={handleSubmit}>
          <label>
            username:
            <input type="text" name="username" />
          </label>
          <label>
            password:
            <input type="password" name="password" />
          </label>
          <input type="submit" value="Submit" />
        </form>
      </div>
    </div>
  );
}
