import { CliQuickstartPage } from './app.po';

describe('cli-quickstart App', () => {
  let page: CliQuickstartPage;

  beforeEach(() => {
    page = new CliQuickstartPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
