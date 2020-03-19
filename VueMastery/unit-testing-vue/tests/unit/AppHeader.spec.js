import AppHeader from '@/components/AppHeader'
import { mount } from '@vue/test-utils'

describe('AppHeader', () => {
  test('If the user is not logged in, do not show the Logout button', () => {
    const wrapper = mount(AppHeader)
    expect(wrapper.find('button').isVisible()).toBe(false)
  })

  test('If the user is logged in, show the Logout button', async () => {
    const wrapper = mount(AppHeader)
    wrapper.setData({ loggedIn: true }) // setup the data
    await wrapper.vm.$nextTick() // wait for DOM to be updated with the setup data
    expect(wrapper.find('button').isVisible()).toBe(true)
  })
})
