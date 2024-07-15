export const oktaConfig = {
    clientId: '0oaic34nl4ingBCXT5d7',
    issuer: 'https://dev-77985930.okta.com/oauth2/default',
    redirectUri: 'http://localhost:3000/login/callback',
    scopes: ['openid', 'profile', 'email'],
    pkce: true,
    disableHttpsCheck: true,
}