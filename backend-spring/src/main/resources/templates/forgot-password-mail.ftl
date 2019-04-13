<html>
<head>
  <title>Bank Management - Reset Password</title>

  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

  <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

  <style>
    body {
      font-family: 'Roboto', sans-serif;
      font-size: 48px;
    }
  </style>
</head>
<body style="margin: 0; padding: 0;">

<table align="center" border="0" cellpadding="0" cellspacing="0" width="600"
       style="border-collapse: collapse;">
  <tr>
    <td align="center" bgcolor="#457aaa" style="padding: 40px 0 30px 0;">
      <h2 style="color: azure;">Reset Password</h2>
    </td>
  </tr>
  <tr>
    <td bgcolor="#eaeaea" style="padding: 40px 30px 40px 30px;">
      <p>Dear ${firstName} ${lastName},</p>
      <p>Here is your token to reset your password: ${token}</p>
      <p>Or you can just follow this link: ${frontEnd}/reset-password?username=${username}&token=${token}</p>
      <p>The link and token are valid until: ${expireDate} at ${expireTime}</p>
      <p>Thanks</p>
    </td>
  </tr>
  <tr>
    <td bgcolor="#a2a2a2" style="padding: 30px 30px 30px 30px;">
      <p>Bank Management</p>
      <p>Morocco</p>
    </td>
  </tr>
</table>

</body>
</html>
